Public Class Requisitante
    Private Sub Requisitante_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.ControlBox = False
        Me.REQUISITANTETableAdapter.Fill(Me.BDbEdi2021DataSet4.REQUISITANTE)
    End Sub

    Private Sub ButtonNovo_Click(sender As Object, e As EventArgs) Handles ButtonNovo.Click
        REQUISITANTEBindingSource.AddNew()
    End Sub

    Private Sub ButtonGuardar_Click(sender As Object, e As EventArgs) Handles ButtonGuardar.Click
        REQUISITANTEBindingSource.EndEdit()
        REQUISITANTETableAdapter.Update(BDbEdi2021DataSet4)
        MessageBox.Show("Dados guardados com sucesso :)")
    End Sub

    Private Sub ButtonApagar_Click(sender As Object, e As EventArgs) Handles ButtonApagar.Click
        REQUISITANTEBindingSource.RemoveCurrent()
    End Sub

    Private Sub ButtonAnterior_Click(sender As Object, e As EventArgs) Handles ButtonAnterior.Click
        Me.Hide()
        Gerencia.Show()
    End Sub

    Private Sub ButtonProximo_Click(sender As Object, e As EventArgs) Handles ButtonProximo.Click
        Me.Hide()
        Sala.Show()
    End Sub

    Private Sub ButtonFechar_Click(sender As Object, e As EventArgs) Handles ButtonFechar.Click
        Application.Exit()
    End Sub
End Class