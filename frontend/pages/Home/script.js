const baseUrl = "http://localhost:8080"

async function loginUser() {

    let title = document.getElementById("spaceTitle").value
    let description = document.getElementById("spaceDesc").value
    let token = document.body.

    let json = JSON.stringify({
        "title" : title,
        "description" : description,
        "token" : 
    })

    console.log(json)

    const response = await fetch(
        `${baseUrl}/auth`,
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

    localStorage.setItem("token", "Bearer " + result)
}

window.loginUser = loginUser