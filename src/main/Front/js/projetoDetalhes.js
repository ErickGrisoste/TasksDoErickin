// pega o id do projeto da URL -> exemplo: projeto-detalhes.html?id=1


const urlParams = new URLSearchParams(window.location.search);
const projetoId = urlParams.get("id");
let listaDeTarefas;

// carrega os dados do projeto
async function carregarProjeto() {
  try {
    const resp = await fetch(`http://localhost:8080/projetos/${projetoId}`);
    if (!resp.ok) throw new Error("Erro ao carregar projeto");

    const projeto = await resp.json();

    document.getElementById("projeto-nome").textContent = projeto.nome;
    document.getElementById("projeto-descricao").textContent = projeto.descricao;
    document.getElementById("projeto-data-inicial").textContent = projeto.dataInicio || "Não informado";
    document.getElementById("projeto-data-fim").textContent = projeto.dataFim || "Não informado";
    document.getElementById("projeto-criador").textContent = projeto.criador.username;

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
    listaDeTarefas = tarefas;

    const tbody = document.getElementById("tarefas-lista");
    tbody.innerHTML = "";

    tarefas.forEach(tarefa => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${tarefa.nome}</td>
        <td>${tarefa.descricao}</td>
        <td>${tarefa.responsavel?.username || "Sem responsável"}</td>
        <td><span class="status ${tarefa.status}">${tarefa.status}</span></td>
        <td class="acao">
          <button onclick="abrirModal(${tarefa.id})" class="btnStatus" ">
            Ver Detalhes
          </button>
        </td>
      `;

      tbody.appendChild(tr);
    });

  } catch (err) {
    console.error(err);
    alert("Erro ao carregar tarefas");
  }
}


function addTarefa() {
  window.location.href = `cadTarefas.html?projetoId=${projetoId}`;
}

function voltarTelaInicial() {
  window.location.href = "telaInicial.html";
}

async function abrirModal(idTarefa) {

  console.log(idTarefa);

  const resp = await fetch(`http://localhost:8080/tarefas/${idTarefa}`);
  if (!resp.ok) throw new Error("Erro ao carregar tarefas");

  const dadosTarefa = await resp.json();
  console.log(dadosTarefa);

  document.getElementById('modal-content').innerHTML = `
    <button class="btnSair" onclick="fecharModal() ">
      <img src="../Img/cross-small.png" alt="Voltar" style="width: 40px; height: 40px; ">
    </button>

    <h3>${dadosTarefa.nome}</h3>
    
    <p>${dadosTarefa.descricao}</p>
    
    <div class="painelStatus">
      
      <p>
        <span class="status ${dadosTarefa.status}">
          ${dadosTarefa.status}
        </span>
      </p>

      <button class="alterarStatus" >Alterar Status</button>

    </div>

    <p>Responsável: ${dadosTarefa.responsavel.username}</p>
    
    <button class="excluirTarefa">Excluir Tarefa</button>
  `

  document.getElementById('meuModal').style.display = 'flex';
}

function fecharModal() {
  document.getElementById('meuModal').style.display = 'none';
}


carregarProjeto();
