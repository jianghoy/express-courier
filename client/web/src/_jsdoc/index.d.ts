
declare type TGetPriceAndTime=(dest: string, orig:string, callback: TPriceCallback)=>void

/**
 * callback handles priceAndTime
 */
declare type TPriceCallback=(priceAndTime: Array<TPriceAndTime>)=>void


declare type TPriceAndTime={
    price: number,
    type: string,
}



declare type TOrder={
    billingAddress: TAddress,
    id: number,
    pickUpAddress: TAddress,
    price: number,
    shippingAddress: TAddress,
    status: string,
    type: string,
    //TODO: figure out how to use wareHouse
    wareHouse: object,
}

declare type TAddress={
    address: string,
    city: string,
    country: string,
    id: number,
    state: string,
    zipCode: string
}

// declare type TRobot={
//     capacity: number,
//     id: number,
//     maxWeight: number,
//     speed: number
// }
