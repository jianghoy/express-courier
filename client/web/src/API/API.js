/// <reference path="../_jsdoc/index.d.ts" />

/**
 * @type{TGetPriceAndTime}
 * 
 */
export function getPriceAndTime(dest, orig,callback) {
    let fetchURL = "/ec/price?dest=" + dest + "&orig=" + orig;
    console.log(fetchURL);
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => callback(data));
}
