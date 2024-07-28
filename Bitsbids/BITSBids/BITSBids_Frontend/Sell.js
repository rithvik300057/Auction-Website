document.addEventListener('DOMContentLoaded', function () {
    const sellForm = document.getElementById('sell-form');

    sellForm.addEventListener('submit', function (e) {
        e.preventDefault();

        // Retrieve form input values
        const productTitle = document.getElementById('product-title').value;
        const productDescription = document.getElementById('product-description').value;
        const startingBid = parseFloat(document.getElementById('starting-bid').value);
        const productCategory = document.getElementById('product-category').value;
        const timeLeft = parseInt(document.getElementById('time-left').value); // Get time left value

        // Perform basic form validation for required fields
        if (
            productTitle.trim() === '' ||
            productDescription.trim() === '' ||
            isNaN(startingBid) || startingBid <= 0 ||
            productCategory === '' ||
            isNaN(timeLeft) || timeLeft <= 0
        ) {
            alert('Please fill in all required fields and ensure the input values are valid.');
            return;
        }

        const productData = {
            name: productTitle,
            description: productDescription,
            price: startingBid,
            category: productCategory,
            currentBid: startingBid, 
            timeLeft: timeLeft,
        };
        

        fetch('http://localhost:8080/product/sell', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(productData),
        })
            .then(response => response.json())
            .then(createdProduct => {
                alert('Product created successfully!');
                // Clear the form fields after successful submission
                document.getElementById('product-title').value = '';
                document.getElementById('product-description').value = '';
                document.getElementById('starting-bid').value = '';
                document.getElementById('product-category').value = '';
                document.getElementById('time-left').value = ''; // Clear the time left input

            })
            .catch(error => {
                alert('Error creating the product. Please try again.');
                console.error('Error:', error);
            });
    });
});
