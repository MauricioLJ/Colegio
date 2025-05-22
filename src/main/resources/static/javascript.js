document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("registroForm");
 
    form.addEventListener("submit", function (e) {
        e.preventDefault();
 
        const estudiante = {
            nombre: document.getElementById("nombre").value,
            primer_apellido: document.getElementById("primer_apellido").value,
            segundo_apellido: document.getElementById("segundo_apellido").value,
            fecha_nacimiento: document.getElementById("fecha_nacimiento").value,
            nota: parseInt(document.getElementById("nota").value),
            estado: "Activo"
        };
 
        const materia = {
            nombre_materia: document.getElementById("nombre_materia").value,
            tipo_materia: document.getElementById("tipo_materia").value,
            nota: parseInt(document.getElementById("nota").value)
        };
 
        const data = {
            estudiante: estudiante,
            materia: materia
        };
 
        fetch("/api/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) throw new Error("Error en la solicitud");
            return response.json();
        })
        .then(result => {
            alert("✅ " + result.mensajeEstudiante + "\n📘 " + result.mensajeMateria);
            form.reset(); 
        })
        .catch(error => {
            console.error("Error:", error);
            alert("❌ Ocurrió un error al registrar. Revisa la consola.");
        });
    });
});

