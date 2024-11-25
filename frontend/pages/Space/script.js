const baseUrl = "http://localhost:8080"

async function postQuestion() {

    let question = document.getElementById("questionText").value
    let token = localStorage.getItem("token").split(" ")

    token = token[1]

    let json = JSON.stringify({
        "title" : title,
        "description" : description,
        "token" : token
    })

    console.log(json)

    const response = await fetch(
        `${baseUrl}/spaces`,
        {
            method: "POST",
            headers: {
                "Authorization" : localStorage.getItem("token"),
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

window.postSpace = postSpace