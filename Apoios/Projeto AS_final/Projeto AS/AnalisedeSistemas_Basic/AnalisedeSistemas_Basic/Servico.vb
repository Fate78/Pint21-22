Public Class Servico
    Private Sub Servico_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.ControlBox = False
        Me.SERVICOTableAdapter.Fill(Me.BDbEdi2021DataSet10.SERVICO)
    End Sub

    Private Sub ButtonNovo_Click(sender As Object, e As EventArgs) Handles ButtonNovo.Click
        SERVICOBindingSource.AddNew()
    End Sub

    Private Sub ButtonGuardar_Click(sender As Object, e As EventArgs) Handles ButtonGuardar.Click
        SERVICOBindingSource.EndEdit()
        SERVICOTableAdapter.Update(BDbEdi2021DataSet10)
        MessageBox.Show("Dados guardados com sucesso :)")
    End Sub

    Private Sub ButtonApagar_Click(sender As Object, e As EventArgs) Handles ButtonApagar.Click
        SERVICOBindingSource.RemoveCurrent()
    End Sub

    Private Sub ButtonAnterior_Click(sender As Object, e As EventArgs) Handles ButtonAnterior.Click
        Me.Hide()
        Participacao.Show()
    End Sub

    Private Sub ButtonProximo_Click(sender As Object, e As EventArgs) Handles ButtonProximo.Click
        Me.Hide()
        Servico_Sala.Show()
    End Sub

    Private Sub ButtonFechar_Click(sender As Object, e As EventArgs) Handles ButtonFechar.Click
        Application.Exit()
    End Sub
End Class