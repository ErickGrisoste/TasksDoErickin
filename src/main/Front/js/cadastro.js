const API_BASE = "http://localhost:8080";

        async function cadastrar() {
            const username = document.getElementById("username").value;
            const email = document.getElementById("email").value;
            const senha = document.getElementById("senha").value;

            if (!username || !email || !senha) {
                alert("Preencha todos os campos!");
                return;
            }

            try {
                const response = await fetch(`${API_BASE}/usuarios`, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ username, email, senha })
                });

                if (response.ok) {
                    alert("Usuário cadastrado com sucesso!");
                    window.location.href = "login.html";
                } else {
                    alert("Erro ao cadastrar usuário!");
                }
            } catch (error) {
                alert("Erro ao conectar com o servidor.");
            }
        }

        function cancelar() {
            document.getElementById("username").value = "";
            document.getElementById("email").value = "";
            document.getElementById("senha").value = "";

            window.location.href = "login.html";
        }