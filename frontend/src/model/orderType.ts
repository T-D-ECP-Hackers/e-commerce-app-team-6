import {basketProduct} from "./basketProductType";

export type order = {
    id: string;
    dateTimeOfOrder: string;
    totalCost: number;
    orderedProducts: basketProduct[];
    completed: boolean;
}