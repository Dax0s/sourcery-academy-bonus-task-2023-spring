import axios from "axios";
import { useEffect, useState } from "react";
import EmployeeTable from "./MainPageElements/EmployeeTable";
import FileInput from "./MainPageElements/FileInput";

const MainPage = () => {
    let [data, setData] = useState([]);

    const fetchEmployees = () => {
        axios.get('/api/employee')
        .then((res) => {
            setData(res.data);
        })
        .catch(console.log);
    }

    useEffect(() => {
        data = [];
        fetchEmployees();
    }, []);
    return (
        <div>
            <FileInput onPost={fetchEmployees} />
            {data && <EmployeeTable data={data} />}
        </div>
    );
}

export default MainPage;