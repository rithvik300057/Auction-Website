document.addEventListener('DOMContentLoaded', function () {
    function fetchAndDisplayProducts() {
        fetch('http://localhost:8080/product')
            .then(response => response.json())
            .then(products => {
                displayProducts(products);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }

    function displayProducts(products) {
        const productContainer = document.getElementById('product-container');

        products.forEach(product => {
            const existingProductSection = document.getElementById(`product-${product.pid}`);
            
            if (existingProductSection) {
                // If the product section already exists, update its content
                updateProductSection(existingProductSection, product);
            } else {
                // If the product section doesn't exist, create a new one
                const productSection = createProductSection(product);
                productContainer.appendChild(productSection);
            }
        });
    }

    function updateProductSection(productSection, product) {
        // Update the content of an existing product section
        const currentBid = productSection.querySelector('.current-bid');
        const timeLeft = productSection.querySelector('.time-left');

        currentBid.innerText = `Current Bid: ₹${product.currentBid.toFixed(2)}`;
        timeLeft.innerText = `Time Left: ${product.timeLeft} hrs`;
    }

    function createProductSection(product) {
        // Create a new product section
        const productSection = document.createElement('section');
        productSection.id = `product-${product.pid}`;
        productSection.className = 'product';

        const productName = document.createElement('h1');
        productName.innerText = product.name;

        const productDescription = document.createElement('p');
        productDescription.innerText = product.description;

        const currentBid = document.createElement('p');
        currentBid.className = 'current-bid';
        currentBid.innerText = `Current Bid: ₹${product.currentBid.toFixed(2)}`;

        const timeLeft = document.createElement('p');
        timeLeft.className = 'time-left';
        timeLeft.innerText = `Time Left: ${product.timeLeft} hrs`;

        const bidAmountInput = document.createElement('input');
        bidAmountInput.type = 'number';
        bidAmountInput.id = `bidAmount-${product.pid}`;
        bidAmountInput.placeholder = 'Enter your bid amount';

        const placeBidButton = document.createElement('button');
        placeBidButton.innerText = 'Place Bid';
        placeBidButton.dataset.pid = product.pid;

        productSection.appendChild(productName);
        productSection.appendChild(productDescription);
        productSection.appendChild(currentBid);
        productSection.appendChild(timeLeft);
        productSection.appendChild(bidAmountInput);
        productSection.appendChild(placeBidButton);

        return productSection;
    }

    function handlePlaceBid(event) {
        const productId = event.target.dataset.pid;
        const bidAmountInput = document.getElementById(`bidAmount-${productId}`);
        const bidAmount = parseFloat(bidAmountInput.value);
    
        // Assuming there's a function to fetch product details by ID
        fetch(`http://localhost:8080/product/${productId}`)
            .then(response => response.json())
            .then(product => {
                if (!isNaN(bidAmount) && bidAmount > product.currentBid) {
                    // You can call updateCurrentBid(productId, bidAmount) here
                    alert(`Bid placed with amount ₹${bidAmount.toFixed(2)}`);
                    // Update the displayed bid amount if needed
                    updateProductSection(document.getElementById(`product-${productId}`), {
                        ...product,
                        currentBid: bidAmount
                    });
                } else {
                    alert('Please enter a valid Bid Amount or a bid greater than the current bid.');
                }
            })
            .catch(error => {
                console.error('Error fetching product details:', error);
            });
    }
    
    // Function to update the product section with new data
    function updateProductSection(productSection, product) {
        const currentBid = productSection.querySelector('.current-bid');
        const timeLeft = productSection.querySelector('.time-left');
    
        currentBid.innerText = `Current Bid: ₹${product.currentBid.toFixed(2)}`;
        timeLeft.innerText = `Time Left: ${product.timeLeft} hrs`;
    }
    

    function filterProducts() {
        const searchInput = document.getElementById('product-search');
        const searchTerm = searchInput.value.toLowerCase();

        const productSections = document.querySelectorAll('.product');

        productSections.forEach((section) => {
            const productName = section.querySelector('h1').innerText.toLowerCase();
            const isVisible = productName.includes(searchTerm);
            section.style.display = isVisible ? 'block' : 'none';
        });
    }

    fetchAndDisplayProducts();
    
    // Fetch and display products every 30 seconds (adjust the interval as needed)
    setInterval(fetchAndDisplayProducts, 30000);

    document.getElementById('product-container').addEventListener('click', function (event) {
        // Check if the clicked element is a button with the 'Place Bid' text
        if (event.target.tagName === 'BUTTON' && event.target.innerText === 'Place Bid') {
            handlePlaceBid(event);
        }
    });

    document.getElementById('product-search').addEventListener('input', filterProducts);
});
