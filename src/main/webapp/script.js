document.getElementById('movieForm')?.addEventListener('submit', function(event) {
    event.preventDefault(); // Stops the page from reloading when you click submit

    // 1. Grab the values the user typed into your form input fields
    const titleValue = document.getElementById('movieTitle').value;
    const genreValue = document.getElementById('movieGenre').value;

    // 2. Package the values into a clean object data format
    const movieData = {
        title: titleValue,
        genre: genreValue
    };

    console.log("Sending movie data to backend:", movieData);

    // 3. Send the data to Seethea's Java Servlet backend pathway
    fetch('MovieServlet', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(movieData)
    })
    .then(response => {
        if (response.ok) {
            alert("Movie successfully added to your watchlist!");
            document.getElementById('movieForm').reset(); // Clears the input boxes
        } else {
            alert("Server error occurred while adding movie.");
        }
    })
    .catch(error => {
        console.error('Error connecting to backend:', error);
        alert("Could not connect to the backend server path.");
    });
});