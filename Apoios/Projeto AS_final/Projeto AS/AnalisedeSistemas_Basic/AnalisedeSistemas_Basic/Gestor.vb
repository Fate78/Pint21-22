Public Class Gestor
    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.ControlBox = False
        Me.GESTORTableAdapter.Fill(Me.BDbEdi2021DataSet.GESTOR)
    End Sub

    Private Sub ButtonNovo_Click(sender As Object, e As EventArgs) Handles ButtonNovo.Click
        GESTORBindingSource.AddNew()
    End Sub

    Private Sub ButtonGuardar_Click(sender As Object, e As EventArgs) Handles ButtonGuardar.Click
        GESTORBindingSource.EndEdit()
        GESTORTableAdapter.Update(BDbEdi2021DataSet)
        MessageBox.Show("Dados guardados com sucesso :)")
    End Sub

    Private Sub ButtonApagar_Click(sender As Object, e As EventArgs) Handles ButtonApagar.Click
        GESTORBindingSource.RemoveCurrent()
    End Sub

    Private Sub ButtonProximo_Click(sender As Object, e As EventArgs) Handles ButtonProximo.Click
        Me.Hide()
        Departamento.Show()
    End Sub

    Private Sub ButtonFechar_Click(sender As Object, e As EventArgs) Handles ButtonFechar.Click
        Application.Exit()
    End Sub
End Class
