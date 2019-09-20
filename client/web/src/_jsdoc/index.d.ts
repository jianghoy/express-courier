
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
    customer: TCustomer,
    id: number,
    items: Array<TItem>,
    pickUpAddress: TAddress,
    price: number,
    robot: TRobot,
    shippingAddress: TAddress,
    status: string,
    type: boolean,
    wareHouse: object,
}

declare type TAddress={
    address: string,
    city: string,
    country: string,
    customer: TCustomer,
    id: number,
    state: string,
    zipCode: string
}