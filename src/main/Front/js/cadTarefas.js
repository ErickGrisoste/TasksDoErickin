const API_BASE = "http://localhost:8080";

const urlParams = new URLSearchParams(window.location.search);
const projetoIdParam = urlParams.get("projetoId");

let usuariosCache = [];
let projetosCache = [];

// ---------- CARREGAR DADOS DO BACK ----------
async function carregarUsuarios() {
  try {
    const resp = await fetch(`${API_BASE}/usuarios`);
    if (!resp.ok) throw new Error("Erro ao carregar usuários");
    usuariosCache = await resp.json();
  } catch (err) {
    console.error(err);
    alert("Erro ao carregar lista de usuários.");
  }
}

async function carregarProjetos() {
  try {
    const resp = await fetch(`${API_BASE}/projetos`);
    if (!resp.ok) throw new Error("Erro ao carregar projetos");
    projetosCache = await resp.json();
  } catch (err) {
    console.error(err);
    alert("Erro ao carregar lista de projetos.");
  }

  if (projetoIdParam) {
    const projeto = projetosCache.find(p => p.id == projetoIdParam);
    if (projeto) {
      document.getElementById("inputProjeto").value = projeto.nome;
    }
  }
}

// ---------- AUTOCOMPLETE GENÉRICO ----------
function setupAutocomplete(inputEl, sugestoesEl, lista, campoNome) {
  inputEl.addEventListener("input", () => {
    const valor = inputEl.value.toLowerCase();
    sugestoesEl.innerHTML = "";

    if (valor.length === 0) {
      sugestoesEl.style.display = "none";
      return;
    }
    const filtrados = lista.filter(item => item[campoNome].toLowerCase().includes(valor));

    if (filtrados.length > 0) {
      sugestoesEl.style.display = "block";

      filtrados.forEach(item => {
        const div = document.createElement("div");
        div.classList.add("sugestao");

        if (campoNome === "username")
          div.textContent = item.username;

        if (campoNome === "nome")
          div.textContent = item.nome;

        div.addEventListener("click", () => {
          inputEl.value = item[campoNome];
          sugestoesEl.innerHTML = "";
          sugestoesEl.style.display = "none";
        });
        sugestoesEl.appendChild(div);
      });

    } else {
      sugestoesEl.style.display = "none";
      sugestoesEl.innerHTML = "";
    }


  });
}

// ---------- FORM SUBMIT ----------
document.getElementById("formTarefa").addEventListener("submit", async (e) => {
  e.preventDefault();

  const responsavelNome = document.getElementById("inputResponsavel").value;
  const projetoNome = document.getElementById("inputProjeto").value;

  const responsavelSelecionado = usuariosCache.find(u => u.username === responsavelNome);
  const projetoSelecionado = projetosCache.find(p => p.nome === projetoNome);

  if (!responsavelSelecionado) {
    alert("Responsável inválido! Selecione um usuário da lista.");
    return;
  }

  if (!projetoSelecionado) {
    alert("Projeto inválido! Selecione um projeto da lista.");
    return;
  }

  const tarefa = {
    nome: document.getElementById("nome").value,
    descricao: document.getElementById("descricao").value,
    status: document.getElementById("status").value,
    responsavel: { id: responsavelSelecionado.id },
    projeto: { id: projetoSelecionado.id }
  };

  try {
    const resp = await fetch(`${API_BASE}/tarefas`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(tarefa)
    });

    if (resp.ok) {
      alert("Tarefa cadastrada com sucesso!");
      window.location.href = `projetoDetalhes.html?id=${projetoIdParam}`;
    } else {
      alert("Erro ao cadastrar tarefa.");
    }
  } catch (err) {
    console.error(err);
    alert("Erro de conexão com a API.");
  }
});


function cancelar(){
  window.location.href = `projetoDetalhes.html?id=${projetoIdParam}`;
}





// ---------- INICIALIZAÇÃO ----------
(async function init() {
  await carregarUsuarios();
  await carregarProjetos();

  setupAutocomplete(
    document.getElementById("inputResponsavel"),
    document.getElementById("sugestoesResponsavel"),
    usuariosCache,
    "username"
  );

  setupAutocomplete(
    document.getElementById("inputProjeto"),
    document.getElementById("sugestoesProjeto"),
    projetosCache,
    "nome"
  );
})();
