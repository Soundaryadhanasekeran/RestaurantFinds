<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Restaurant Finder</title>
  <style>
    /* ===== Background Animation ===== */
    body {
      font-family: 'Segoe UI', sans-serif;
      margin: 0;
      padding: 0;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      background: linear-gradient(270deg, #ff9a9e, #fad0c4, #fbc2eb, #a6c1ee);
      background-size: 800% 800%;
      animation: gradientMove 15s ease infinite;
    }

    @keyframes gradientMove {
      0% { background-position: 0% 50%; }
      50% { background-position: 100% 50%; }
      100% { background-position: 0% 50%; }
    }

    /* ===== Container Card ===== */
    .container {
      background: rgba(255, 255, 255, 0.9);
      padding: 25px 30px;
      border-radius: 16px;
      box-shadow: 0 8px 20px rgba(0,0,0,0.2);
      width: 420px;
      backdrop-filter: blur(10px);
      animation: fadeIn 1s ease-in-out;
    }

    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(20px); }
      to { opacity: 1; transform: translateY(0); }
    }

    /* ===== Form Elements ===== */
    h2 {
      text-align: center;
      margin-bottom: 20px;
      color: #333;
    }

    label {
      display: block;
      margin: 10px 0 5px;
      font-weight: bold;
      color: #444;
    }

    input, select, button {
      width: 100%;
      padding: 12px;
      margin-bottom: 15px;
      border: 1px solid #ddd;
      border-radius: 10px;
      font-size: 16px;
    }

    input:focus {
      outline: none;
      border-color: #6a5acd;
      box-shadow: 0 0 8px rgba(106, 90, 205, 0.5);
    }

    button {
      background: linear-gradient(90deg, #6a5acd, #7b68ee);
      color: white;
      font-weight: bold;
      border: none;
      cursor: pointer;
      transition: all 0.3s ease;
    }

    button:hover {
      background: linear-gradient(90deg, #483d8b, #6a5acd);
      transform: scale(1.05);
    }

    /* ===== Results ===== */
    .results {
      margin-top: 20px;
      max-height: 250px;
      overflow-y: auto;
    }

    .restaurant {
      background: #fafafa;
      border-radius: 10px;
      padding: 12px;
      margin-bottom: 12px;
      box-shadow: 0 2px 6px rgba(0,0,0,0.1);
      transition: transform 0.2s;
    }

    .restaurant:hover {
      transform: scale(1.02);
      background: #fff;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>🍴 Restaurant Finder</h2>
    <form id="restaurantForm">
      <label for="cuisine">Cuisine Type:</label>
      <input type="text" id="cuisine" placeholder="e.g. Japanese, Vegetarian" required>

      <label for="rating">Minimum Rating (0.0 - 5.0):</label>
      <input type="number" id="rating" step="0.1" min="0" max="5" required>

      <button type="submit">Find Restaurants</button>
    </form>

    <div class="results" id="results"></div>
  </div>

  <script>
    document.getElementById("restaurantForm").addEventListener("submit", async function(event) {
      event.preventDefault();

      const cuisine = document.getElementById("cuisine").value.trim();
      const rating = parseFloat(document.getElementById("rating").value);

      // Call your backend API here:
      // Example:
      // const response = await fetch(`http://localhost:8080/api/restaurants?cuisine=${cuisine}&rating=${rating}`);
      // const data = await response.json();

      // Temporary mock data for testing
      const data = [
        {name: "Sakura Sushi", cuisine_type: "Japanese", address: "123 Main St", rating: 4.5},
        {name: "Veggie Delight", cuisine_type: "Vegetarian", address: "45 Green Rd", rating: 4.2}
      ];

      const resultsDiv = document.getElementById("results");
      resultsDiv.innerHTML = "";

      const filtered = data.filter(r => 
        r.cuisine_type.toLowerCase() === cuisine.toLowerCase() && r.rating >= rating
      );

      if (filtered.length === 0) {
        resultsDiv.innerHTML = "<p>No restaurants found for your preferences.</p>";
      } else {
        filtered.forEach(r => {
          const div = document.createElement("div");
          div.classList.add("restaurant");
          div.innerHTML = `
            <strong>${r.name}</strong><br>
            🍽 Cuisine: ${r.cuisine_type}<br>
            📍 Address: ${r.address}<br>
            ⭐ Rating: ${r.rating}
          `;
          resultsDiv.appendChild(div);
        });
      }
    });
  </script>
</body>
</html>
