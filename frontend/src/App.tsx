import { BrowserRouter, Routes } from "react-router-dom";
import { LeadsRoutes } from "./routes/leadsRoutes";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {LeadsRoutes()}
      </Routes>
    </BrowserRouter>
  );
}

export default App;