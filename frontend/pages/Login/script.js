const baseUrl = "http://localhost:8080"

async function loginUser() {

    let pass = document.getElementById("senhaInput").value
    let edv = document.getElementById("edvInput").value

    let json = JSON.stringify({
        "pass" : pass,
        "edv" : edv
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