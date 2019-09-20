
declare type TGetPriceAndTime=(dest: string, orig:string, callback: TPriceCallback)=>void

/**
 * callback handles priceAndTime
 */
declare type TPriceCallback=(priceAndTime: Array<TPriceAndTime>)=>void

declare type TPriceAndTime={
    price: number,
    type: string,
}