

$(document).ready(function() {
    // Attacher un gestionnaire d'événements au soumission du formulaire
    $('#paiementForm').submit(function(e) {
        // Empêcher le comportement par défaut du formulaire (rechargement de la page)
        e.preventDefault();

        // Collecter les données du formulaire
        var formData = $(this).serialize();

        // Effectuer la requête Ajax
        $.ajax({
            type: 'POST', // ou 'GET' selon vos besoins
            url: '/devis/payerDevisAjax', // L'URL du script serveur qui traitera les données
            data: formData,
            dataType: 'json',
            success: function(data) {
                // Gérer la réponse du serveur
                $('#resultat').html('Réponse du serveur : ' + data.message);
            },
            error: function(xhr, status, error) {
                if(xhr.responseText=="Mety") {
                    console.log(xhr.responseText);
                } else {
                    var error=document.getElementById("error");
                    var divErrorContain=document.createElement("div");
                    divErrorContain.classList.add("alert");
                    divErrorContain.classList.add("alert-danger");
                    error.appendChild(divErrorContain);
                    divErrorContain.innerHTML=xhr.responseText;
                    console.log(xhr.responseText);
                    // document.getElementById("error").innerHTML=xhr.responseText;
                }
                $('#resultat').html('Erreur Ajax : ' + xhr.status + ' ' + xhr.statusText);
            }
        });
    });
});