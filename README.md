# express-courier

A unmanned courier service application using React, React Native and Spring-boot.

## Tutorials

Please refer to `\tutorial\` for extensive helps.

## PR wanted

We are just a small group of developers so please help us with all you've got. Anything is appreciated, and Pull Requests are extremely welcomed.

## Installation

### Front end

We are using *React* for front end, please run following commands on terminal to install packages needed

`
npm install // install packages needed
`

In order to test our front end, you will need a google map api key. Please [apply](https://developers.google.com/maps/documentation/javascript/get-api-key) one here if you haven't done so already.

After you get the api key, create a file called **credentials.json** under /client/web/src/Components and add

`
{
    "GOOGLEMAPAPI_KEY" : 'your key here'
}
`

replace 'your key here' with your Google Map API key and hit save. 

..

To start the project, run
`
npm start
`

### Back end