const baseUrl = "http://localhost:8080"

async function registerUser()  {

    let name = document.getElementById("nomeInput").value
    let email = document.getElementById("emailInput").value
    let ps = document.getElementById("senhaInput").value
    let edv = document.getElementById("edvInput").value
    
    let json = JSON.stringify({
        "name": name,
        "email": email,
        "pass": ps,
        "edv": edv,
    })
    console.log(json)

    const response = await fetch(
        `${baseUrl}/user`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: json
        }
    )    

    console.log(response.data);
}


window.registerUser = registerUser