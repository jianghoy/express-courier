const axios = require('axios');
axios.get('http://localhost:8080/ec/price',{
        param:{
            orig:'golden gate',
            dest:'SFO'
        }
    })
    .then(response => console.log(response))
    .catch(e => console.log(e))
    