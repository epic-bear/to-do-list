function openPopup(popupId) {
    document.getElementById(popupId).style.display = "block";
}

function closePopup(popupId) {
    document.getElementById(popupId).style.display = "none";
}

function addAction() {
    const actionDescription = document.getElementById("actionDescription").value;
    if (!actionDescription) {
        alert("Please enter a description.");
        return;
    }

    const username = document.getElementById("username").value;

    // Create an ActionDTO object
    const actionData = {
        description: actionDescription,
        username: username
    };

    // Send a POST request to create the action
    fetch("/actions", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(actionData),
    })
        .then(response => {
            if (response.status === 201) {
                closePopup("addActionPopup");
                window.location.href = `/actions?username=` + username;
            } else {
                alert("Action creation failed.");
            }
        });
}

function openEditPopup(id, description, status) {
    document.getElementById("editActionDescription").value = description;
    document.getElementById("editActionStatus").value = status;
    document.getElementById("editActionId").value = id;

    document.getElementById("editActionPopup").style.display = "block";
}

function updateAction() {
    const actionId = document.getElementById("editActionId").value;
    const actionDescription = document.getElementById("editActionDescription").value;
    const actionStatus = document.getElementById("editActionStatus").value;

    if (!actionDescription) {
        alert("Please enter a description.");
        return;
    }

    const actionData = {
        description: actionDescription,
        status: actionStatus
    };

    fetch(`/actions/${actionId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(actionData),
    })
        .then(response => {
            if (response.status === 200) {
                closePopup("editActionPopup");
                window.location.reload();
            } else {
                alert("Action update failed.");
            }
        });
}

function getUsernameFromURL() {
    const currentURL = window.location.href;

    const searchParams = new URLSearchParams(currentURL);

    return searchParams.get("username");
}