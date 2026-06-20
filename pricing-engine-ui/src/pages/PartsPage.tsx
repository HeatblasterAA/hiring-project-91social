import { useEffect, useState } from "react";
import api from "../services/api";
import { Part } from "../types/Part";

function PartsPage() {
  const [parts, setParts] = useState<Part[]>([]);
  const [name, setName] = useState("");
const [category, setCategory] = useState("FRAME");
const [description, setDescription] = useState("")

  useEffect(() => {
    fetchParts();
  }, []);
const createPart = async () => {
  try {
    await api.post("/parts", {
      name,
      category,
      description,
    });

    setName("");
    setCategory("FRAME");
    setDescription("");

    fetchParts();
  } catch (error) {
    console.error(error);
  }
};
  const fetchParts = async () => {
    try {
   const response = await api.get<Part[]>("/parts");
      setParts(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Parts Management</h1>

      <ul>
        {parts.map((part) => (
          <li key={part.id}>
            {part.name} - {part.category}
          </li>
        ))}
      </ul>
      <h2>Create Part</h2>

<input
  placeholder="Part Name"
  value={name}
  onChange={(e) => setName(e.target.value)}
/>

<select
  value={category}
  onChange={(e) => setCategory(e.target.value)}
>
  <option value="FRAME">FRAME</option>
  <option value="TYRE">TYRE</option>
  <option value="GEAR">GEAR</option>
  <option value="BRAKE">BRAKE</option>
  <option value="SEAT">SEAT</option>
  <option value="HANDLEBAR">HANDLEBAR</option>
</select>

<input
  placeholder="Description"
  value={description}
  onChange={(e) => setDescription(e.target.value)}
/>

<button onClick={createPart}>
  Create Part
</button>
    </div>
  );
}

export default PartsPage;