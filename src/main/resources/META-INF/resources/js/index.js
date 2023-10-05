// Function to open a popup by ID
function openPopup(popupId) {
    document.getElementById(popupId).style.display = "block";
}

// Function to close a popup by ID
function closePopup(popupId) {
    document.getElementById(popupId).style.display = "none";
}

function createUser() {
    const createUsername = document.getElementById("createUsername").value;
    if (!createUsername) {
        alert("Please enter a username.");
        return;
    }
    fetch(`/user/${createUsername}`, {
        method: "POST",
    })
        .then(response => {
            if (response.status === 200) {
                alert("User created successfully!");
                // Redirect to the actions page
                window.location.href = '/actions?username=' + createUsername;
            } else {
                alert("User creation failed.");
            }
        });
}

function signIn() {
    const signInUsername = document.getElementById("signInUsername").value;
    if (!signInUsername) {
        alert("Please enter a username.");
        return;
    }
    fetch(`/user/${signInUsername}`)
        .then(response => {
            if (response.status === 200) {
                alert("Sign In Successful!");
                // Redirect to the actions page
                window.location.href = '/actions?username=' + signInUsername;
            } else {
                alert("Sign In failed. User not found.");
            }
        });
}