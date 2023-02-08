import axios from "axios";
import { useEffect, useState } from "react";
import EmployeeTable from "./MainPageElements/EmployeeTable";
import FileInput from "./MainPageElements/FileInput";
import styles from "./MainPage.module.scss";

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
        <div className={styles.container}>
            <div className={styles.input}>
                <FileInput onPost={fetchEmployees} />
            </div>
            {data.length > 0 && <EmployeeTable data={data} />}
        </div>
    );
}

export default MainPage;