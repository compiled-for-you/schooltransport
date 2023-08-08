document.getElementById("fb").addEventListener('click', function(){
    fetch('/getData')
    .then(response => response.text())
    .then(data => {
      displayData(data); // Call a function to display the fetched data
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
});

function displayData(data){
    console.log("data is " + data);
}

// document.getElementById("fb").addEventListener('click', function(){
//   console.log('clidckeds f');
// });

document.getElementById('twitter').addEventListener('click' ,function(){
  fetch('http://localhost:8081/organizations/get')
    .then(response => response.json())
    .then(data => {
      console.log(data.length);
      const jsonString = JSON.stringify(data, null, 2); // Using 2 spaces for indentation
    
    // Log the formatted JSON string to the console
      console.log(jsonString);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
});