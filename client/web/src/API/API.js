/// <reference path="../_jsdoc/index.d.ts" />

/**
 * get various price options based on destination and origin
 * @param {string} dest destination of order
 * @param {string} orig starting point of order
 * @param {TPriceCallback} callback callback function: put response handling here
 */
export function getPriceAndTime(dest, orig, callback) {
    let fetchURL = "/ec/price?dest=" + dest + "&orig=" + orig;
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => callback(data));
}

//TODO:wire up APIs

/**
 * get multiple orders based on pagination; the user is get via session
 * @param {number} page numbers of pages starting from 0
 * @param {number} size how many orders per page
 * @param {TGetOrdersByPagiCb} callback input arguments: orders: an array of order; hasNext: has next page
 */
export function getOrdersByPagination(page, size, callback) {
    let fetchURL = "/ec/order?page=" + page + "&size=" + size;
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => callback(data.orders,data.hasNext))               
}

/**
 * get one order by id
 * @param {*} id 
 * @param {*} callback 
 */
export function getOrderById(id, callback) {
    let fetchURL = "/ec/order/" + id;
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => callback(data));
}

