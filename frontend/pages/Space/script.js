const baseUrl = "http://localhost:8080"

async function postQuestion() {

    let question = document.getElementById("questionText").value
    let spaceId = localStorage.getItem("spaceId")

    let json = JSON.stringify({
        "question": question,
        "spaceId": spaceId
    })

    console.log(json)

    const response = await fetch(
        `${baseUrl}/question`,
        {
            method: "POST",
            headers: {
                "Authorization": localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: json
        }
    )

    let result = await response.text();

    console.log(result)

    if (result == "Erro ao criar a pergunta!") {
        alert(result)
    } else {
        alert(result)
        window.location.reload()
    }
}

window.postQuestion = postQuestion 

baseurl = "http://localhost:8080"

const renderQuestions = async () => {

    const id = localStorage.getItem("spaceId")

    console.log(id);

    try {
        const response = await fetch(
            `${baseUrl}/question?query=''&page=0&spaceId=${id}&limit=`,
            {
                method: "GET",
                headers: {
                    "Authorization": localStorage.getItem("token"),
                    "Content-Type": "application/json"
                }
            }
        );

        const data = await response.json();

        console.log(data)

        if (data) {

            var div = document.getElementById("questions")

            var spaceNameDiv = document.getElementById("spaceName")

            spaceNameDiv.innerText = data[0].spaceName

            if (data[0].description != null) {
                data.forEach(element => {
                    div.insertAdjacentHTML("beforeend", 
                        `
                            <div class="card col-12 col-md-9 col-lg-6">
                                <div class="card-body d-flex flex-column">
                                    <b class="card-title">${element.username}</b>
                                    <p class="card-text">${element.description}</p>
                                    <a onclick="seeAnswers(${element.questionId})" class="btn btn-secondary align-self-end">VER RESPOSTAS</a>
                                </div>
                            </div>
                        `)
                });
            }

        } else {
            console.error('Error:', data);
        }
    } catch (error) {
        console.error('Error:', error);
    }

}

async function getUsers() {
    const id = localStorage.getItem("spaceId")

        const response = await fetch (
            `${baseUrl}/user?query=''&page=0&size=100`,
            {
                method: "GET",
                headers: {
                    "Authorization": localStorage.getItem("token"),
                    "Content-Type": "application/json"
                }
            }
        );

        const data = await response.json();

        console.log(data);

        var div = document.getElementById("usersList")

        data.forEach(element => {
            div.insertAdjacentHTML("beforeend",
            `
            <div class="d-flex gap-3 w-75 justify-content-between">
                <h6>${element.name}</h6>
                <div class="d-flex flex-column flex-lg-row justify-content-end align-items-end gap-2">
                    <button id="set_admin_button" class="btn btn-light">Adminstrador</button>
                    <button id="set_member_button" class="btn btn-light">Membro</button>
                    <button id="set_none_button" class="btn btn-light">Desconhecido</button>
                </div>
            </div>
            `
        )
        })
    
}

function seeAnswers(id) {
    localStorage.setItem("questionId", id)

    window.location.href = "../Question"
}

document.addEventListener("DOMContentLoaded", renderQuestions)

window.getUsers