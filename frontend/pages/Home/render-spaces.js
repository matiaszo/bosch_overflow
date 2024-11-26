
const baseurl = "http://localhost:8080"

async function getSpaces() {
    
    console.log("oioioi");
    
    // fazendo a requisição completa, endpoint com headers e body

    console.log(localStorage.getItem("token"));

    const response = await fetch(
        "http://localhost:8080/spaces?query=&page=&limit=",
        {
            method: "GET",
            headers: {
                "Authorization": localStorage.getItem("token"),
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }
    )
    
    return await response.json()
    
}


 async function renderSpaces() {
    
    const spaces = await getSpaces();

    console.log(spaces);
    
    const div = document.getElementById("spaces");

    spaces.forEach(space => {
        div.insertAdjacentHTML("beforeend",
        `<div class="card col-12 col-md-9 col-lg-6 w-25" id=${space.id}>
            <div class="card-body d-flex flex-column">
                <h5 class="card-title">${space.title}</h5>
                <p class="card-text">${space.description}</p>
                <a id="access_space_button" rel="keep-params" onclick="seeQuestion(${space.id})" class="btn btn-primary align-self-end">ACESSAR</a>
            </div>
        </div>`)
    })

}

function seeQuestion(id) {
    // const url = new URL("http://127.0.0.1:5500/pages/Space");

    // url.searchParams.set("spaceId", id);    // Use URLSearchParams to add query param
    // url.searchParams.set("query", "");      // Use URLSearchParams to add query param
    // url.searchParams.set("page", 1);        // Use URLSearchParams to add query param
    // url.searchParams.set("size", 2);        // Use URLSearchParams to add query param

    // window.location.href = url.href;

    // INFELIZMENTE SERÁ NECESSÁRIA UMA GAMBIARRA

    localStorage.setItem("questionId", id)

    window.location.href = "../Space"
}   

document.seeQuestion = seeQuestion
document.addEventListener("DOMContentLoaded", renderSpaces)