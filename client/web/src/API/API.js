/// <reference path="../_jsdoc/index.d.ts" />

import {ACCESS_TOKEN} from '../Const'

//TODO: merge request and requestText
/**
 * 
 * @param {*} options 
 * @param {number} resBodyType 1:json;2:text
 */
export const request = (options,resBodyType) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};

export const requestText = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.text().then(text => {
            if(!response.ok) {
                return Promise.reject(text);
            }
            return text;
        })
    );
};

/**
 * get various price options based on destination and origin
 * @param {string} dest destination of order
 * @param {string} orig starting point of order
 * @param {TPriceCallback} callback callback function: put response handling here
 */
export function getPriceAndTime(dest, orig, callback) {
    let fetchURL = "/price?dest=" + dest + "&orig=" + orig;
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => callback(data));
}

//TODO:wire up APIs

/**
 * get multiple orders based on pagination; the user is get via JWT token
 * @param {number} page numbers of pages starting from 0
 * @param {number} size how many orders per page
 * @param {TGetOrdersByPagiCb} callback input arguments: orders: an array of order; hasNext: has next page
 */
export function getOrdersByPagination(page, size, callback) {
    if (!page) {
        page = 0;
    }
    if (!size) {
        size = 5;
    } 
    let fetchURL = "/order?page=" + page + "&size=" + size;
    request({
        url:fetchURL,
        method: 'GET',
    }).then(data => callback(data));
}

/**
 * get one order by id
 * @param {*} id 
 * @param {*} callback 
 */
export function getOrderById(id, callback) {
    let fetchURL = "/order/" + id;
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => callback(data));
}

export function register(regInfo) {
    return request({
        url:'/signup',
        method: 'POST',
        body: JSON.stringify(regInfo)
    })
}

export function login(loginInfo) {
    return request({
        url:'/signin',
        method: 'POST',
        body: JSON.stringify(loginInfo)
    })
}

/** @returns {Promise} */
export function checkout(order) {
    return requestText({
        url:'/checkout',
        method: 'PUT',
        body: JSON.stringify(order)
    })
}

/**
 * @param {string} string 
 * @returns {TAddress} address in TAddress format
 */
export function strToTAddress(string) {
    var strings = string.split(',')

    /** @type {TAddress} */
    var address = {address:"",city:"",state:"",country:""};
    address.address = strings[0].trim();
    address.city = strings[1].trim();
    address.state = strings[2].trim();
    address.country = strings[3].trim();
    return address;   

}