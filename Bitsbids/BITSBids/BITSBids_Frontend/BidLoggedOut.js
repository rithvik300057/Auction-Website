document.getElementById('fetchData').addEventListener('click', () => {
    fetch('/secured', { // Assuming this is the endpoint in your Spring Boot controller
        method: 'GET', // Ensure the method is 'GET'
        // Other request parameters
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response data
        console.log(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
