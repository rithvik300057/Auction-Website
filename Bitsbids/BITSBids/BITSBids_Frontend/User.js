document.addEventListener('DOMContentLoaded', function () {
    const userForm = document.getElementById('user-form');

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();

        // Retrieve form input values
        const userName = document.getElementById('user-name').value;
        const userBatch = parseInt(document.getElementById('user-batch').value);
        const userEmail = document.getElementById('user-email').value;
        const userContact = document.getElementById('user-contact').value;

        // Perform basic form validation for required fields
        if (
            userName.trim() === '' ||
            userEmail.trim() === '' ||
            isNaN(userBatch) || userBatch <= 0 ||
            userContact.trim() === ''
        ) {
            alert('Please fill in all required fields and ensure the details are valid.');
            return;
        }

        const userData = {
            uname: userName,
            ubatch: userBatch,
            uemail: userEmail,
            ucontact: userContact,
        };

        fetch('http://localhost:8080/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        })
            .then(response => response.json())
            .then(createdUser => {
                alert('User created successfully!');
                // Clear the form fields after successful submission
                document.getElementById('user-name').value = '';
                document.getElementById('user-batch').value = '';
                document.getElementById('user-email').value = '';
                document.getElementById('user-contact').value = '';
            })
            .catch(error => {
                alert('Error creating the user. Please try again.');
                console.error('Error:', error);
            });
    });
});
