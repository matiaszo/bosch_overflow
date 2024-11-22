import axios from "../../../../node_modules/axios/"

const baseUrl = "http://localhost:8080"

const api = axios.create({})

api.post(`${baseUrl}/user`, {
    firstName: 'Santos',
    lastName: 'Dumont'
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.error(error);
  });