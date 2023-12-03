var typeOfUser='';
function selectUserType(userType){
    document.querySelectorAll('.icon').forEach(icon => {
        icon.classList.remove('selected')
    })
    // Add the 'selected' class to the clicked icon
    document.getElementById(`${userType}Icon`).classList.add('selected');
    document.getElementById('nextBtn').disabled=false;
    typeOfUser=userType;
}

document.getElementById('nextBtn').addEventListener('click', function(){
    console.log(typeOfUser);
    document.getElementById('commonForm').hidden=true;
    document.getElementById('userSpecificForm').hidden=false;
    document.getElementById(`${typeOfUser}RegistrationForm`).hidden=false;
});

function postData() {
    const form = document.getElementById('userRegistrationForm');
    const formData = new FormData(form);
    
    const userType = typeOfUser;
    const url = `/register/${userType}`;

    // Convert form data to JSON
    const jsonObject = {};
    jsonObject['roles'] = typeOfUser;
    formData.forEach((value, key) => {
        if(!(value==null || value==''))
            jsonObject[key] = value;
        console.log(key+"->"+value);
    });
    const jsonData = JSON.stringify(jsonObject);
    console.log("Ultimatley JSON data is "+jsonData);

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonData,
    })
    .then(response => {
        // if (!response.ok) {
        //     throw new Error('Network response was not ok');
        // }
        return response.json();
    })
    .then(data => {
        // Handle the response data
        console.log(data);
        console.log(data.redirectUrl);
        console.log(data.successMessage);
        if(data.errorMessage!=null) throw new Error(data.errorMessage);
        if(data.redirectUrl!=null)
            window.location.href=data.redirectUrl;
    })
    .catch(error => {
        // Handle errors
        console.error('There was a problem with the fetch operation:', error);
    });
}

