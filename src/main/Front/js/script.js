const API_BASE = "http://localhost:8080";
var idProjeto;

// Função para buscar projetos
async function fetchProjetos() {
    const response = await fetch(`${API_BASE}/projetos`);
    const projetos = await response.json();

    const projetosList = document.getElementById("projetos-list");
    projetosList.innerHTML = "";

    projetos.forEach(projeto => {
        const li = document.createElement("li");

        li.innerHTML = `<strong>${projeto.nome}</strong> <br>
            Criado por: <strong>${projeto.criador.username}</strong>
            <button class="btn-ver" data-id="${projeto.id}">Ver mais</button>
            <div class="descricao" style="display: none; margin-top: 5px;">
                ${projeto.descricao || "Sem descrição"}
            </div>`;

        projetosList.appendChild(li);
    });

    document.querySelectorAll(".btn-ver").forEach(botao => {
        botao.addEventListener("click", () => {
            const descricaoDiv = botao.nextElementSibling;
            if (descricaoDiv.style.display === "none") {
                descricaoDiv.style.display = "block";
                botao.textContent = "Esconder";
            } else {
                descricaoDiv.style.display = "none";
                botao.textContent = "Ver mais";
            }
        });
    });
}

// Função para buscar tarefas de um projeto
async function fetchTarefas(projetoId) {
    const response = await fetch(`${API_BASE}/tarefas/projeto/${projetoId}`);
    const tarefas = await response.json();

    const tarefasList = document.getElementById("tarefas-list");
    tarefasList.innerHTML = "";

    tarefas.forEach(tarefa => {
        const li = document.createElement("li");
        li.innerHTML = `${tarefa.nome} - <strong>${tarefa.status}</strong> <br>
            Responsável: ${tarefa.responsavel.username}`;
        tarefasList.appendChild(li);
    });
}

// Inicializa a página
fetchProjetos();
