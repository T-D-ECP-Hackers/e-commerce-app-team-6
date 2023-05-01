export type basket = {
    id: number;
    username: string;
    totalProducts: number;
    basketProducts: basketProduct[];
}

export type basketProduct = {
    id: number;
    product: product;
    count: number
}

export type product = {
    id: number;
    name: string;
    description: string;
    price: number;
}