const API_BASE = "http://localhost:8080";

// Função para buscar projetos
async function fetchProjetos() {
    const response = await fetch(`${API_BASE}/projetos`);
    const projetos = await response.json();

    const projetosList = document.getElementById("projetos-list");
    projetosList.innerHTML = "";

    projetos.forEach(projeto => {
        const li = document.createElement("li");

        li.innerHTML = `<strong> ${projeto.nome} </strong> <br> ${projeto.descricao}<br><br>
            Criado por: <strong> ${projeto.criador.username} </strong>`;

        li.addEventListener("click", () => fetchTarefas(projeto.id));
        projetosList.appendChild(li);
    });
}

var teste = 0;

// Função para buscar tarefas de um projeto
async function fetchTarefas(projetoId) {
    const response = await fetch(`${API_BASE}/tarefas/projeto/${projetoId}`);
    const tarefas = await response.json();

    const tarefasList = document.getElementById("tarefas-list");
    tarefasList.innerHTML = "";

    tarefas.forEach(tarefa => {
        const li = document.createElement("li");

        li.innerHTML = `${tarefa.nome} - <strong> ${tarefa.status} </strong> <br>
            Responsável: ${tarefa.responsavel.username}`;

        li.addEventListener("click", () => {
            if(teste == 0){
                li.innerHTML += `<p>${tarefa.descricao}</p>`;
                teste = 1;
            } else{
                li.removeChild(li.lastChild);
                teste = 0;
            }

          });
        tarefasList.appendChild(li);
    });
}

// Inicializa a página
fetchProjetos();
