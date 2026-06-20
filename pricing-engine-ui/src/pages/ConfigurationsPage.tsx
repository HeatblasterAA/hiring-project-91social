import { useEffect, useState } from "react";
import api from "../services/api";
import { Part } from "../types/Part";

function ConfigurationsPage() {

  const [parts, setParts] = useState<Part[]>([]);

  const [configurationName, setConfigurationName] =
    useState("");

  const [selectedPartId, setSelectedPartId] =
    useState<number>(0);

  const [quantity, setQuantity] =
    useState<number>(1);

  const [configurationParts, setConfigurationParts] =
    useState<
      {
        partId: number;
        quantity: number;
      }[]
    >([]);

  useEffect(() => {
    fetchParts();
  }, []);

  const fetchParts = async () => {
    const response =
      await api.get<Part[]>("/parts");

    setParts(response.data);
  };

  const addPart = () => {

    if (!selectedPartId) return;

    setConfigurationParts([
      ...configurationParts,
      {
        partId: selectedPartId,
        quantity,
      },
    ]);
  };

  const createConfiguration =
    async () => {

      try {

        await api.post(
          "/configurations",
          {
            name:
              configurationName,
            parts:
              configurationParts,
          }
        );

        alert(
          "Configuration Created"
        );

        setConfigurationName("");

        setConfigurationParts([]);

      } catch (error) {

        console.error(error);

      }
    };

  return (
    <div>

      <h1>Configurations</h1>

      <input
        placeholder="Configuration Name"
        value={configurationName}
        onChange={(e) =>
          setConfigurationName(
            e.target.value
          )
        }
      />

      <br />
      <br />

      <select
        onChange={(e) =>
          setSelectedPartId(
            Number(
              e.target.value
            )
          )
        }
      >
        <option value="">
          Select Part
        </option>

        {parts.map((part) => (
          <option
            key={part.id}
            value={part.id}
          >
            {part.name}
          </option>
        ))}
      </select>

      <input
        type="number"
        value={quantity}
        onChange={(e) =>
          setQuantity(
            Number(
              e.target.value
            )
          )
        }
      />

      <button onClick={addPart}>
        Add Part
      </button>

      <ul>
        {configurationParts.map(
          (part, index) => (
            <li key={index}>
              Part ID:
              {part.partId}
              {" | "}
              Quantity:
              {part.quantity}
            </li>
          )
        )}
      </ul>

      <button
        onClick={
          createConfiguration
        }
      >
        Create Configuration
      </button>

    </div>
  );
}

export default ConfigurationsPage;