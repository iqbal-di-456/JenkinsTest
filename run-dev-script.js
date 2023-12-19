function myMethod() {
    console.log('Hello from DEV myMethod!');

    const { exec } = require('child_process');

    // Define your Fastlane command
    const fastlaneCommand = 'fastlane my_lane';

    // Execute the Fastlane command
    exec(fastlaneCommand, (error, stdout, stderr) => {
    if (error) {
        console.error(`Error executing Fastlane: ${error}`);
        return;
    }

    console.log(`Fastlane output: ${stdout}`);
    });

}

myMethod(); // This will call the method when the script is executed.
