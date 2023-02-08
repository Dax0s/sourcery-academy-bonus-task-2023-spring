import styles from "./EmployeeTable.module.scss";

type Props = {
    data: any
}

const EmployeeTable = ({ data }: Props) => {
    return (
        <table className={styles.container}>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone number</th>
                </tr>
            </thead>
            <tbody>
                {
                    data.map((employee: any) => (
                        <tr key={employee.id}>
                            <td>{employee.name}</td>
                            <td>{employee.email}</td>
                            <td>{employee.phoneNumber}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    )
}
export default EmployeeTable;