const baseUrl = "http://localhost:8080"

async function postQuestion() {

    let question = document.getElementById("questionText").value
    let token = localStorage.getItem("token").split(" ")

    token = token[1]

    let json = JSON.stringify({
        "title": title,
        "description": description,
        "token": token
    })

    console.log(json)

    const response = await fetch(
        `${baseUrl}/spaces`,
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

    if (result == "Já existe um espaço com este nome!") {
        alert(result)
    } else {
        alert(result)
        window.location.reload()
    }
}

window.postSpace = postSpaceconst

baseurl = "http://localhost:8080"

const renderQuestions = async () => {

    const id = localStorage.getItem("questionId")

    console.log(id);
    console.log("ooo");

    try {
        const response = await fetch(
            `${baseurl}/question?spaceId=${id}`,
            {
                method: "GET",
                headers: {
                    "Authorization": localStorage.getItem("token"),
                    "Content-Type": "application/json"
                }
            }
        );

        const data = await response.json();

        if (response.ok) {

            var div = document.getElementById("questions")

            response.array.forEach(element => {
                div.insertAdjacentHTML("beforeend", 
                    `
                        <div class="card col-12 col-md-9 col-lg-6">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">${element.question}</h5>
                                <p class="card-text">${element.question}</p>
                                <a href="../Question/" class="btn btn-secondary align-self-end">VER RESPOSTAS</a>
                            </div>
                        </div>
                    `)
            });

        } else {
            console.error('Error:', data);
        }
    } catch (error) {
        console.error('Error:', error);
    }

}

document.addEventListener("DOMContentLoaded", renderQuestions)