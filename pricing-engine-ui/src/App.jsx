import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

import PartsPage from "./pages/PartsPage";
import ConfigurationsPage from "./pages/ConfigurationsPage";
import PricingPage from "./pages/PricingPage";

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Parts</Link> |{" "}
        <Link to="/configurations">Configurations</Link> |{" "}
        <Link to="/pricing">Pricing</Link>
      </nav>

      <Routes>
        <Route path="/" element={<PartsPage />} />
        <Route
          path="/configurations"
          element={<ConfigurationsPage />}
        />
        <Route path="/pricing" element={<PricingPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;