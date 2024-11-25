const baseUrl = "http://localhost:8080"

async function postSpace() {

    let title = document.getElementById("spaceTitle").value
    let description = document.getElementById("spaceDesc").value
    let token = localStorage.getItem("token")

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
                "Content-Type": "application/json"
            },
            body: json
        }
    )

    let result = await response.text();

    console.log(result)
}

window.postSpace = postSpace