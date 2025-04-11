const playerOneValue = document.getElementById('playerOneValue');
const playerTwoValue = document.getElementById('playerTwoValue');
const submitButton = document.getElementById('submitButton');
const resultElements = document.querySelectorAll('.result');

function toggleSubmitButton() {
    submitButton.disabled = playerOneValue.value === "" || playerTwoValue.value === "";

    if (!submitButton.disabled) {
        resultElements.forEach(element => {
            element.innerHTML = "";
        });
    }
}