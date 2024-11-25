
const baseurl = "http://localhost:8080"

async function getSpaces() {
    
    console.log("oioioi");
    
    // fazendo a requisição completa, endpoint com headers e body

    console.log(localStorage.getItem("token"));

    // const body = {
    //     "name": document.getElementById("space-name").value
    // }
    
    const response = await fetch(
        `${baseurl}/spaces?query=""`,
        {
            method: "GET",
            headers: {
                "Authorization": localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            // body: JSON.stringify(body)
        }
    )
    
    return await response.json()
    
}


 async function renderSpaces() {
    
    const spaces = await getSpaces();

    console.log(spaces);
    
    const div = document.getElementById("spaces");

    // spaces.forEach(space => {
    //     div.insertAdjacentHTML("beforeend", space.toString())
    // })

}

document.addEventListener("DOMContentLoaded", renderSpaces)