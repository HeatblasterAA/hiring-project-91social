import { ComponentPriceResponse } from "./ComponentPriceResponse";

export interface PricingResponse {
  configurationName: string;
  components: ComponentPriceResponse[];
  grandTotal: number;
}