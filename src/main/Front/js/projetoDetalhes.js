// pega o id do projeto da URL -> exemplo: projeto-detalhes.html?id=1


const urlParams = new URLSearchParams(window.location.search);
const projetoId = urlParams.get("id");

// carrega os dados do projeto
async function carregarProjeto() {
  try {
    const resp = await fetch(`http://localhost:8080/projetos/${projetoId}`);
    if (!resp.ok) throw new Error("Erro ao carregar projeto");

    const projeto = await resp.json();

    document.getElementById("projeto-nome").textContent = projeto.nome;
    document.getElementById("projeto-descricao").textContent = projeto.descricao;
    document.getElementById("projeto-data").textContent = projeto.dataInicio || "Não informado";

    carregarTarefas(projetoId);
  } catch (err) {
    console.error(err);
    alert("Erro ao carregar projeto");
  }
}

// carrega as tarefas do projeto
async function carregarTarefas(projetoId) {
  try {
    const resp = await fetch(`http://localhost:8080/tarefas/projeto/${projetoId}`);
    if (!resp.ok) throw new Error("Erro ao carregar tarefas");

    const tarefas = await resp.json();
    const tbody = document.getElementById("tarefas-lista");
    tbody.innerHTML = "";

    tarefas.forEach(tarefa => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${tarefa.nome}</td>
        <td>${tarefa.descricao}</td>
        <td>${tarefa.responsavel?.username || "Sem responsável"}</td>
        <td><span class="status ${tarefa.status}">${tarefa.status}</span></td>
      `;

      tbody.appendChild(tr);
    });

  } catch (err) {
    console.error(err);
    alert("Erro ao carregar tarefas");
  }
}

carregarProjeto();
