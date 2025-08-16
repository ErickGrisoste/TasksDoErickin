const API_BASE = "http://localhost:8080";
let usuariosCache = [];

// Carregar usuários uma vez
 async function carregarUsuarios() {
   try {
     const response = await fetch(`${API_BASE}/usuarios`);
     if (!response.ok) throw new Error("Erro ao carregar usuários");
     usuariosCache = await response.json();
   } catch (error) {
     console.error(error);
     alert("Erro ao carregar lista de usuários.");
   }
 }

 const inputCriador = document.getElementById("criador");
 const listaSugestoes = document.getElementById("sugestoes");

 // Mostrar sugestões conforme digita
 inputCriador.addEventListener("input", () => {
   const valor = inputCriador.value.toLowerCase();
   listaSugestoes.innerHTML = "";

   if (valor.length === 0) return;

   const filtrados = usuariosCache.filter(u => u.username.toLowerCase().includes(valor));

   filtrados.forEach(user => {
     const li = document.createElement("li");
     li.textContent = user.username;
     li.addEventListener("click", () => {
       inputCriador.value = user.username;
       listaSugestoes.innerHTML = ""; // limpa as sugestões
     });
     listaSugestoes.appendChild(li);
   });
 });

// Enviar projeto
document.getElementById("formProjeto").addEventListener("submit", async (e) => {
  e.preventDefault();

  const nomeDigitado = inputCriador.value;
  const usuarioSelecionado = usuariosCache.find(u => u.username === nomeDigitado);

  if (!usuarioSelecionado) {
    alert("Usuário inválido! Selecione um usuário válido da lista.");
    return;
  }

  const projeto = {
    nome: document.getElementById("nome").value,
    descricao: document.getElementById("descricao").value,
    dataInicio: document.getElementById("dataInicio").value,
    dataFim: document.getElementById("dataFim").value,
    criador: { id: usuarioSelecionado.id }
  };

  try {
    const response = await fetch(`${API_BASE}/projetos`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(projeto)
    });

    if (response.ok) {
      alert("Projeto cadastrado com sucesso!");
      window.location.href = "telaInicial.html";
    } else {
      alert("Erro ao cadastrar projeto.");
    }
  } catch (error) {
    console.error("Erro:", error);
    alert("Erro de conexão com a API.");
  }
});

carregarUsuarios();
