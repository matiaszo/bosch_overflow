const baseurl = "http://localhost:8080"

const renderQuestions = async ( ) => {
    const id = new URLSearchParams(window.location.search).get("spaceId");
    console.log(id); //
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
            window.location.href = "../meus-pacientes";
        } else {
            console.error('Error:', data);
        }
    } catch (error) {
        console.error('Error:', error);
    }
    
}

document.addEventListener("DOMContentLoaded", renderQuestions)