
// ════════════════ Examples ════════════════════ //
/** 
TODO: Review the following function. This function creates and returns a new Promise object. 
    Note the following about the creation of the Promise:
    - The Promise constructor takes in one argument, an executor.
    - The executor is responsible for initiating an asynchronous operation and changing the state of the Promise to either "fulfilled" or "rejected".
    - The executor takes in two callback functions as arguments.
        -  If the operation was successful, the executor should call the first callback function, typically named "resolve", with the resolved value as its argument.
        - Calling the resolve function with a value will transition the Promise object to the "fulfilled" state, allowing the Promise to be processed further using ".then()".
        - If the operation failed, the executor should call the second callback function, typically named "reject", with the an error or error message as its argument.
        - Calling the reject function with an error will transition the Promise object to the "rejected" state, allowing the Promise to be processed further using ".catch()".

No edits to the function need to be made.

    @param {number} value - A value that is used in the executor's operation
    @returns {Promise} - A Promise that contains the resolved or rejected value
*/
function isEvenPromise(value) {
    return new Promise(
        // executor
        (resolve, reject) => {

            // simulated asynchronous operation: checking if a number is even, but with a 1-3 second delay
            setTimeout(() => {
                const result = value % 2 === 0;

                if (result) {
                    // call resolve and pass in value necessary
                    resolve("The value " + value + " is even!");
                } else {
                    // call reject and pass in value necessary
                    reject("The value " + value + " is odd!");
                }
            }, getRandomDelay());
        });
}

/** 
TODO: Review the following function. This function takes in a promise and changes the text of an HTML element with an id of "output1" to have it's text be the resolved value or the error message. Note the following:
    - we use the `then()` method on our promise to perform actions if the promise was resolved.
    - we use the `catch()` method on our promise to perform actions if the promise was rejected.

No edits to the function need to be made.

    @param {Promise} promise - A Promise that contains the resolved or rejected value
*/
function isEvenPromiseHandler(promise) {

    promise
        .then(resolvedValue => {
            document.getElementById("output1").innerText = resolvedValue;
        })
        .catch(errorValue => { 
            document.getElementById("output1").innerText = errorValue;
        });

}

// ════════════════ Exercise 1 ════════════════════ //
/** 
TODO: This function takes in two numbers as arguments and creates a promise object that resolves to either "The sum is divisible by 5!" or "The sum is NOT divisible by 5!" depending on if the sum of the numbers is divisible by 5 or not. Some of the syntax has been provided for you. Fill in the rest of it's functionality.

    @param {num1} num1 - a number
    @param {num2} num2 - another number
    @returns {Promise} - A Promise that contains the resolved or rejected value
*/
function isDivisibleBy5(num1, num2) {
    return new Promise(
    () => {

        // simulated asynchronous operation: checking if a number is even, but with a 1-3 second delay
        setTimeout(() => {
            
        }, getRandomDelay());
    });
}
// ════════════════ Exercise 2 ════════════════════ //

/** 
TODO: This function should take in a promise as an argument and changes the text of an HTML element with and id of "output2" to have it's text be the resolved value or the error message. Use the `then()` and `catch()` methods.

    @param {Promise} promise - A Promise that contains the resolved or rejected value
*/
function isDivisibleBy5Handler(promise) {

}

// ════════════════════════════════════════════════ //
/* Note: You do not need to edit or view any code below this point. */

function getRandomDelay() {
    const values = [1000, 2000, 3000];
    const randomIndex = Math.floor(Math.random() * values.length);
    return values[randomIndex];
}

async function handleSubmit1(event) {
    // Prevent default form submission behavior
    event.preventDefault();

    // Retrieve form data
    const formData = new FormData(event.target);

    // Process form data
    let data = formData.get("even");
    isEvenPromiseHandler(isEvenPromise(data));

    // Reset the form
    event.target.reset();
}

function handleSubmit2(event) {
    // Prevent default form submission behavior
    event.preventDefault();

    // Retrieve form data
    const formData = new FormData(event.target);

    // Process form data
    let firstNum = formData.get("num1");
    let secondNum = formData.get("num2");
    isDivisibleBy5Handler(isDivisibleBy5(firstNum, secondNum));

    // Reset the form
    event.target.reset();
}

// Get the form element, add event listener
document.getElementById("form1")?.addEventListener("submit", handleSubmit1);
document.getElementById("form2")?.addEventListener("submit", handleSubmit2);

