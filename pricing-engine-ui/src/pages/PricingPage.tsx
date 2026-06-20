import { useEffect, useState } from "react";
import api from "../services/api";
import { Configuration } from "../types/Configuration";
import { PricingResponse } from "../types/PricingResponse";

function PricingPage() {
const [selectedDate,
  setSelectedDate] =
    useState("");
  const [configurations,
    setConfigurations] =
      useState<Configuration[]>([]);

  const [selectedConfiguration,
    setSelectedConfiguration] =
      useState<number>();

  const [pricing,
    setPricing] =
      useState<PricingResponse>();

  useEffect(() => {
    fetchConfigurations();
  }, []);

  const calculateHistoricalPrice =
  async () => {

    if (
      !selectedConfiguration ||
      !selectedDate
    ) return;

    const response =
      await api.get<
        PricingResponse
      >(
        `/pricing/configurations/${selectedConfiguration}/historical`,
        {
          params: {
            date:
              selectedDate
          }
        }
      );

    setPricing(
      response.data
    );
};

  const fetchConfigurations =
    async () => {

      const response =
        await api.get<
          Configuration[]
        >("/configurations");

      setConfigurations(
        response.data
      );
    };

  const calculatePrice =
    async () => {

      if (
        !selectedConfiguration
      ) return;

      const response =
        await api.get<
          PricingResponse
        >(
          `/pricing/configurations/${selectedConfiguration}`
        );

      setPricing(
        response.data
      );
    };

  return (
    <div>

      <h1>
        Pricing Calculator
      </h1>

      <select
        onChange={(e) =>
          setSelectedConfiguration(
            Number(
              e.target.value
            )
          )
        }
      >
        <option value="">
          Select Configuration
        </option>

        {
          configurations.map(
            (config) => (
              <option
                key={config.id}
                value={config.id}
              >
                {config.name}
              </option>
            )
          )
        }
      </select>
      <input
  type="date"
  value={selectedDate}
  onChange={(e) =>
    setSelectedDate(
      e.target.value
    )
  }
/>

      <button
        onClick={
          calculatePrice
        }
      >
        Calculate
      </button>
      <button
  onClick={
    calculateHistoricalPrice
  }
>
  Historical Price
</button>

      {
        pricing && (
          <>
            <h2>
              {
                pricing.configurationName
              }
            </h2>

            <table border={1}>
              <thead>
                <tr>
                  <th>Part</th>
                  <th>Unit Price</th>
                  <th>Quantity</th>
                  <th>Total</th>
                </tr>
              </thead>

              <tbody>

                {
                  pricing.components.map(
                    (
                      component,
                      index
                    ) => (
                      <tr key={index}>
                        <td>
                          {
                            component.partName
                          }
                        </td>

                        <td>
                          {
                            component.unitPrice
                          }
                        </td>

                        <td>
                          {
                            component.quantity
                          }
                        </td>

                        <td>
                          {
                            component.totalPrice
                          }
                        </td>
                      </tr>
                    )
                  )
                }

              </tbody>
            </table>

            <h2>
              Grand Total:
              ₹
              {
                pricing.grandTotal
              }
            </h2>
            

          </>
        )
      }

    </div>
  );
}

export default PricingPage;