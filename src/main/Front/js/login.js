const API_BASE = "http://localhost:8080";

        async function login() {
            const email = document.getElementById("email").value;
            const senha = document.getElementById("senha").value;

            if (!email || !senha) {
                alert("Preencha todos os campos!");
                return;
            }

            try {
                const response = await fetch(`${API_BASE}/usuarios`);
                const usuarios = await response.json();

                const usuarioEncontrado = usuarios.find(
                    u => u.email === email && u.senha === senha
                );

                if (usuarioEncontrado) {
                    alert("Login realizado com sucesso!");
                    window.location.href = "telaInicial.html";
                } else {
                    alert("E-mail ou senha inválidos!");
                }
            } catch (error) {
                alert("Erro ao conectar com o servidor.");
            }
        }

        function cancelar() {
            document.getElementById("email").value = "";
            document.getElementById("senha").value = "";
        }